pipeline {
    agent any
    tools {
        maven 'Maven-3.9.14'
        jdk 'jdk-17'
    }
    environment {
        APP_NAME = 'my-java-app'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 30, unit: 'MINUTES')
        retry(2)
    }
    parameters {
        choice(name: 'DEPLOY_ENVIRONMENT', choices: ['staging', 'production'], description: 'Target environment')
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Run unit tests')
    }
    stages {
        // ----------------- Stage 1: CHECKOUT CODE STAGE -----------------
        stage('Checkout Code') {
            steps {
                branch: 'main'
                git url: 'git@github.com:Nikhil-5453/Automated-CI-CD-with-jenkins.git'
                // ✅ Single checkout — removed duplicate git{} call
                // checkout([
                //     $class: 'GitSCM',
                //     branches: [[name: '*/main']],
                //    // userRemoteConfigs: [[
                //         url: 'git@github.com:Nikhil-5453/Automated-CI-CD-with-jenkins.git',
                //         //credentialsId: 'github-ssh'
                //     //]]
                // ])
                // script {
                //     env.GIT_COMMIT  = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                //     env.GIT_BRANCH  = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
                //     env.APP_VERSION = "1.0.${env.BUILD_NUMBER}"
                // }
                // echo "Checked out commit ${env.GIT_COMMIT} on branch ${env.GIT_BRANCH}"
                // echo "App Version: ${env.APP_VERSION}"
            }
        }
        // ----------------- Stage 2: MAVEN BUILD STAGE -----------------
        stage('Build') {
            steps {
                sh '''
                mvn clean compile \
                -Dmaven.repo.local=$WORKSPACE/.m2/repository \
                -DBUILD_NUMBER=${BUILD_NUMBER} \
                -q
                '''
                
            }
            post {
                success {
                    echo "Build successful! - version ${env.APP_VERSION}" 
                }
                failure {
                    echo "Build failed! - version ${env.APP_VERSION}"
                }
            }
        }


        
        // // ----------------- Stage 3: RUN UNIT TESTS STAGE -----------------
        
        // stage('Run Unit Tests')
        // {
        //     when{
        //         expression { return params.RUN_TESTS }
        //     }
        //     steps{
        //         sh '''
        //         mvn test \
        //         -Dmaven.repo.local=$WORKSPACE/.m2/repository \
        //         -DBUILD_NUMBER=$(BUILD_NUMBER) \
        //         -q
        //         '''
        //     }
        //     post{
        //         always{
        //             junit 'target/surefire-reports/*.xml'
        //             jacaco{
        //                 execPattern 'target/jacaco.exec',
        //                 classPattern 'target/classes',
        //                 sourcePattern 'src/main/java',
        //                 exclusionPattern '**/*Test*.class',
        //                 minimumLineCoverage: '80'
        //             }
        //         }
        //         success{
        //             echo "Tests passed successfully!"
        //         }
        //         failure{
        //             echo "Tests failed!"
        //         }
        //     }
        // }

        // // ----------------- Stage 4: SONARQUBE ANALYSIS STAGE -----------------

        // stage('SonarQube Analysis')
        // {
        //     steps{
        //         withSonarQubeEnv('sonarqube-server'){
        //             sh '''
        //             mvn sonar:sonar \
        //             -Dsonar.projectKey= ${APP_NAME} \
        //             -Dsonar.projectName= ${APP_NAME} \
        //             -Dsonar.projectVersion=${env.APP_VERSION} \
        //             -Dsonar.login=${SONAR_TOKEN} \
        //             -Dmaven.repo.local=$WORKSPACE/.m2/repository \
        //             -DBUILD_NUMBER=$(BUILD_NUMBER) \
        //             -q
        //             '''
        //         }
        //     }
        // }
        // stage('Quality Gate')
        // {
        //     steps{
        //         timeout(time: 5, unit: 'MINUTES'){
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }

        // // ----------------- Stage 5: Package and Deploy to Nexus Stage -----------------

        // stage('Package and Deploy to Nexus')
        // {
        //     steps {
        //         sh '''
        //             mvn package deploy \
        //                 -DrunTests \
        //                 -Dmaven.repo.local=${WORKSPACE}/.m2/repository \
        //                 -DBUILD_NUMBER=${BUILD_NUMBER} \
        //                 -s ${WORKSPACE}/settings.xml
        //         '''
        //         archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        //     }
        //      post{
        //          success{
        //              echo "Artifact deployed to Nexus successfully!"
        //          }
        //          failure{
        //              echo "Failed to deploy artifact to Nexus!"
        //          }
        //      }
        //  }

        // // ----------------- Stage 6: Build and Push Docker Image Stage -----------------

        // stage('Build and push Docker Image')
        // {
        //     steps{
        //         script{
        //             def imageName = "${DOCKER_REGISTRY}/${APP_NAME}:${env.APP_VERSION}"
        //             sh '''
        //             docker build -t ${imageName} .
        //             echo ${DOCKER_CREDENTIALS_PSW} | docker login -u ${DOCKER_CREDENTIALS_USR} --password-stdin ${DOCKER_REGISTRY}
        //             docker push ${imageName}
        //             '''
        //         }
        //     }
        //     post{
        //         success{
        //             echo "Docker image built and pushed successfully!"
        //         }
        //         failure{
        //             echo "Failed to build or push Docker image!"
        //         }
        //     }
        //  }
        
    }
}