pipeline {
    agent any
    options {
        ansiColor('xterm')
        timestamps ()
    }

    stages {
        stage('Test') {
            steps {
                sh './gradlew clean test check funcTest'
            }
            post {
                always {
                    junit 'build/test-results/**/*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                    recordIssues(tools: [pmdParser(pattern: 'build/reports/pmd/*.xml')])
                    recordIssues(tools: [pit(pattern: 'build/reports/pitest/*.xml')])

                }
            }
        }
        stage('Build') {
            steps {
                withGradle {
                    sh './gradlew assemble'
                }
            }

            post {
                success {
                    archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true, followSymlinks: false
                }
            }
        }

        stage('Publish'){
           steps {
                 sshagent(['github-shh']) {
                    sh 'git tag BUILD-1.0.${BUILD_NUMBER}'
                    sh 'git push --tags'
                }
            }
        }
    }
}