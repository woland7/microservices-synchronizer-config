# microservices-synchronizer-config
Configuration repository for microservices *Synchronizer*. It keeps Jenkins pipeline examples and YAML file for configurating a simple continuous deployment pipeline.
It also keeps links to support repositories.

*Synchronizer* could be also implemented with other service registry and also other adapter implementations beside a Jenkins Shared library could be developed. We also thought of implementing *Synchronizer* by using the Sidecar design pattern.

### jenkins-etcdwatcher-sharedlibrary
Jenkins-etcdwatcher-sharedlibrary https://github.com/woland7/jenkins-etcdopenshift-sharedlibrary keeps the code for the implementation of the adatper as a Jenkins shared library.

### etcdwatcher-OpenShift
Etcdwatcher-OpenShift https://github.com/woland7/etcdwatcher-openshift/blob/master/etcdwatcher.go keeps the code for the implementation of the synchronizer for etcd for the OpenShift platform, which has been called etcdwatcher.

### jmeter-docker
Jmeter-docker https://github.com/woland7/jmeter-docker keeps the code for a JMeter container image along with scripts to run tests for TestEtcdWatcher.

### TestEtcdWatcher
TestEtcdWatcher https://github.com/woland7/TestEtcdWatcher is a web application to set up a case study in order to evaluate if *Synchronizer* works as expected. It could be changed as one sees fit.

## How To

This section briefly explains how to set up a CD pipeline on OpenShift/Kubernetes.

### What you need

An active OpenShift cluster

#### Steps

- Spin up a Jenkins microservice from the OpenShift catalogue.

- Spin up a Wildfly microservice from the OpenShift catalogue and use TestEtcdWatcher repositor to create the application

- Use the configurations file provided in this repository to create the imagestream, the buildconfig and the deployconfig to create a JMeter application which will be used to perform the tests.
  - As there is no JMeter default template in the OpenShift catalogue, one should create the application manually by providing the configuration files.
  - Instead, when there are such templates, one could simply use the OpenShift catalogue and then all these operations would performed by the platform under the hood.

- Once the previous steps have been accomplished one should create the Jenkins pipeline. A Jenkins pipeline is also provided in this repository.
  - Of course, IPs should be edited accordingly.

- To use *Synchronizer*, one should set the shared library in Jenkins. Then, as it was done for testing purposes, etcdwatcher could be preloaded on the platform and just downloaded on the pod from there or could instead be downloaded from the repository etcdwatcher-Openshift.
