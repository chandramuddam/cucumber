pipeline {
    agent any
    environment {
        BUILD_TIME = sh(returnStdout: true, script: 'date +%F-%T').trim()
    }

    stages {
        stage("Clean Workspace") {
            steps {
                cleanWs()
            }
        }
        stage('Checkout Self') {
            steps {
                git branch: 'xray_video', credentialsId: '', url: 'https://github.com/chandramuddam/cucumber.git'
            }
        }
        stage('Cucumber Tests') {
            steps {
                withMaven(maven: 'maven35') {
                    sh """
                    cd ${env.WORKSPACE}
                    mvn clean test --fail-at-end
                    """
                }
            }
        }

    }

    post {
        always('Expose report') {
            archiveArtifacts "**/cucumber.json"
            cucumber '**/cucumber.json'

            script {
                def description = "[BUILD_URL|${env.BUILD_URL}]" // BUILD_URL value does matter for this POC
                // def description = "http://172.16.21.186:8080/job/xray/5"
                def labels = '["regression","automated_regression"]'
                def environment = "TEST"
                def testExecutionFieldId = 10007
                def testEnvironmentFieldName = "customfield_10132"
                def projectKey = "XRAYD"
                def xrayConnectorId = 'a3cb4d89-7abd-4ee8-aebd-51b8e0244a44'
                def info = '''{
                        "fields": {
                            "project": {
                            "key": "''' + projectKey + '''"
                        },
                        "labels":''' + labels + ''',
                        "description":"''' + description + '''",
                        "summary": "Cucumber Automated Regression Execution @ ''' + env.BUILD_TIME + ' ' + environment + ''' " ,
                        "issuetype": {
                        "id": "''' + testExecutionFieldId + '''"
                        },
                        "''' + testEnvironmentFieldName + '''" : [
                        "''' + environment + '''"
                        ]
                        }
                        }'''

                echo info
                step([$class: 'XrayImportBuilder', endpointName: '/cucumber/multipart', importFilePath: 'target/cucumber.json', importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId])
            }

       /*     script {
                def description = "[BUILD_URL|${env.BUILD_URL}]" // BUILD_URL value does matter for this POC
                // def description = "http://172.16.21.186:8080/job/xray/5"
                def labels = '["regression","automated_regression"]'
                def environment = "TEST"
                def testExecutionFieldId = 10007
                def testEnvironmentFieldName = "customfield_10132"
                def projectKey = "XRAYD"
                def xrayConnectorId = 'a3cb4d89-7abd-4ee8-aebd-51b8e0244a44'
                def info = '''{
                        "fields": {
                            "project": {
                            "key": "''' + projectKey + '''"
                        },
                        "labels":''' + labels + ''',
                        "description":"''' + description + '''",
                        "summary": "JUnit Automated Regression Execution @ ''' + env.BUILD_TIME + ' ' + environment + ''' " ,
                        "issuetype": {
                        "id": "''' + testExecutionFieldId + '''"
                        },
                        "''' + testEnvironmentFieldName + '''" : [
                        "''' + environment + '''"
                        ]
                        }
                        }'''

                echo info

                step([$class: 'XrayImportBuilder', endpointName: '/junit/multipart', importFilePath: "target/surefire-reports/*.xml", importInfo: info, importToSameExecution: 'true', inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId])
                
            }*/
        }
    }
}
