#Introduction
This whole project is intended to build up a VMware-like tool, which is based on Eve to provide a set of RESTful interfaces, and making full use of libvirt to manage underlying VMs and corresponding peripherals including virtual interface cards, virtual disks and the like.
#Specification
1) libvirt-java-api is kind of deprecated refactored version of the client interfaces and corresponding test cases;

2) pool_management is where all the pool manamgement related files exist including some automatic assistant scripts;

3) RESTful-java-api is the bran-new version which we are trying to make use of Eve to replace former out-dated xmlrpc to achieve RPC and data storing features.

4) RESTful-libvirt is the server-side of the project using Eve as the basic underlying framework and trying to provide a RESTful web service for the java clients and web pages.
#Team
Now this project is supported by only four major members:
Doctor Bear and LHearen is working on underlying server part;
#Contacts
Doctor Bear: wuyuewen11@otcaix.iscas.ac.cn 
LHearen: LHearen@126.com, luosonglei14@otcaix.iscas.ac.cn
