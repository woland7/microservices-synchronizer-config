@Library('etcdwatcher') _
node {
   stage('Build & Deploy') {
    etcdwatcher.downloadEtcdWatcher()
    sh "curl 192.168.10.36:80/master.etcd-ca.crt -o master.etcd-ca.crt"
    sh "curl 192.168.10.36:80/master.etcd-client.key -o master.etcd-client.key"
    sh "curl 192.168.10.36:80/master.etcd-client.crt -o master.etcd-client.crt"
    openshiftBuild(namespace:'tesi', buildConfig:'test-etcdwatcher', showBuildLogs:'true')
    etcdwatcher.watchBuildWithTimeout('tesi', 'test-etcdwatcher', '150', 'atleastonce')
   }
   stage('Test') {
    openshiftDeploy(namespace:'tesi', deploymentConfig:'jmeter-testsuite')
   }
}
