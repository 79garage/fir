pipeline {
    environment {
        DOCKER_REGISTRY = 'registry.gitlab.com'
        IMAGE_PATH = '/i3535/'
        IMAGE_NAME = 'con-refund-inquiry-service'
        buildVersion = "dev.${BUILD_NUMBER}-${GIT_COMMIT[0..7]}"
        IMAGE_TAG = "${DOCKER_REGISTRY}${IMAGE_PATH}${IMAGE_NAME}:${buildVersion}"
        DEPLOY_PATH = "${WORKSPACE}/opt/dev-icd/manifest"
        DEPLOYMENT_FILE = "${DEPLOY_PATH}/deployment.yaml"
        NAME_SPACE = 'sso-con-dev'
    }

    agent any

    stages {

        stage('Build App') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Build and Push image') {
            steps {
                withDockerRegistry(credentialsId: 'gitlab-artit-credentials', url: "https://${DOCKER_REGISTRY}") {
                    script {
                        build_image = docker.build("${IMAGE_TAG}", "--build-arg BUILD_ENV=dev -f opt/dev-icd/Dockerfile .")
                        build_image.push()
                    }
                }
                sh "docker rmi -f ${IMAGE_TAG}"
            }
        }
        stage('Deploy') {
            steps {
                sh 'sed -i "s,IMAGE_REPLACE,$IMAGE_TAG," $DEPLOYMENT_FILE'
                sh 'cat $DEPLOYMENT_FILE'
                withKubeConfig([credentialsId: 'icd-sso-kube']) {
                    sh 'kubectl -n $NAME_SPACE apply -f $DEPLOY_PATH/'
                }
            }
        }

    }

    post {
        always {
            cleanWs()
        }
    }

}