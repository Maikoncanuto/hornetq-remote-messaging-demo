export PWD=`pwd`
export PATH=$PATH:$PWD/rhq-bundle-deployer-4.8.0/bin
rhq-ant -propertyfile deployment.properties
