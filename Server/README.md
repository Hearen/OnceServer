## Development Guide
This Guide is used to lead developers to work on clients based-on this server provided by us, which will including Configure Eve, Configure Mongodb 3.2, Starting our service and the last but also the most important part, APIs supplied both in client demo and server side.

### Configure Eve
We currently are adopting Eve as our basic framework to build our server side, which is coded in python and can be easily installed via pip to achieve global installation. So there are three steps then we need to complete:

1. install pip - there is a [get-pip.py](https://github.com/Hearen/Linux-scripts/tree/master/tools), get it and just run 'python get-pip.py' then everything will be handled automatically if there is something wrong in this process check [this site](https://pip.pypa.io/en/stable/installing/) for some reference.

2. install eve globally - pip install eve.

3. replace some files under '/usr/lib64/python2.7/site-packages/eve/' replace 'endpoints.py' and 'methods/post.py' with the same files under 'Server/Eve'; these files are modified to fit in our requirements.

###Configure Mongodb 3.2
Due to the requirement of our service, we have to provide a database for data storing, meantime the underlying database chosen by Eve is also mongodb, so we have to configure it for further development. There are also two simple steps we need to follow:

1. configure repository under /etc/yum.repos.d/ - run 'vim /etc/yum.repos.d/mongodb.repo' and then copy the following profile to it
```
[mongodb-org-3.2]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/3.2/x86_64/
gpgcheck=0
enabled=1
```

save it and exit.
2. 'yum clean all' and make sure the network connection is alright and then execute this command 'yum install -y mongodb-org' to install mongodb of version 3.2, the latest stable version.

### Java Client API Documentation
Attention
---------
**This part will cover the basic logic and some frequently used methods accomplished so far, which are all tested by the way. As for the return value, there is a rule on all the methods in client side. As for creation, if the operation succeed, server will return the UUID of the newly created object, if failed for duplicate UUID the corresponding message will be delivered but when it failed for some unexpected error, 'User function failed' will be returned as a message. When it comes to all others methods, if it finished successfully, 'true' will be returned but if it encounters some unexpected failures, it will also return 'User function failed!'; there some exceptions in these methods which are inquiring 'true' or 'false' or other result from the server themselves ('isTemplate' and 'listPool' for example) then they will return 'true' or 'false' when succeed but still will return 'User function failed' when encountering some unexpected errors.**

#### VM Management
* create - the parameter accepted is a customized object which will cover the basic and essential elements which creating process will require, besides there are some default values which means that they can be ignored and specified according to the situation. For example, the UUID of the VM can be ignored and then the server will automatically generate one for it, at the same time the client can set it by an exposed interface - setUUID method to set it before creating and then the server will use the provided UUID to create the VM.
* start, shutdown, reboot, delete - all these four methods will require a UUID of the VM to proceed corresponding operation(start, shut off, reboot or delete). 
* isTemplate - is a method to check whether the VM is a template.
* setTemplate, unsetTemplate - to set a VM template or otherwise.

#### Storage Management
Currently there are only some basic operations on storage finished others are still on the way.
* createPool - name and pool absolute path should be provided to create it currently.
* deletePool - the UUID should be provided to specify the pool.
* listPool - this method will return a string composed of pools' names separated by comma.
* createVolume - pool name, volume name and size (default unit 1MB) should be provided currently to create a volume within a pool.
* deleteVolume - pool name and volume name should be provided to delete a volume from a pool.
* listVolumes - list volumes in a pool, so the pool name should be provided currently and the result will a string composed of names separated by comma.

#### VIF Management
* create - just like VM, a customized object will be used to specify the factors creating operation requires and also some values can be ignored and specified in the release; now the UUID, name, source and mac string should be provided to create a virtual network card.
* delete - only UUID required to accomplished this operation.
* attach - to attach a VIF to an existed VM while both are specified by UUID.
* detach - to detach a VIF from an VM while both are specified by UUID.


### API Documentation
This documentation is used to specify the details of the APIs in server side which might be updated when the whole structure comes to its stable state. Currently this part is quite outdated and will be updated in the following few days.

#### Methods Calling Mode
1. The client side (a java or web client) would invoke a encapsulated *POST* method to transfer method information and the parameters the method needs to the server; 

2. the server side will automatically dispatch the called method before the data transferred to the normal *eve handling process* in which process the data will be handled in RESTful way and the data will meantime validated according to the *settings.py*. 

3. due to some extra data being enclosed in the request, the *eve module* will prompt failure in data validation and data insertion, so we have to remove the data undefined in *settings.py* before the checking process in *post.py* of *eve* which is always located in /usr/lib64/python2.7/site-packages/eve/\*.

4. another thing should not be ignored is that the uuidString used in creation process (VM, StoragePool, Volumes and VIF and the like) can be ignored in client side which will use a default one to immitate to keep the data part complete but before the mongodb inserting process, the uuidString must be replaced with the actual one which can be generated by either *libvirt* or *UUIDGenerator.py*.

#### VM Methods
- create -- using some parameters to create a VM and return the uuidString to the client if failed return empty string;
- start -- start a VM specified by uuidString and return *Successful!* if failed return *Failed*;
- shutdown -- shut down a VM specified by uuidString and return *Successful!* if failed return *Failed*;
- delete -- delete a VM specified by uuidString and return *Successful!* if failed return *Failed*;
- reboot -- reboot a VM specified by uuidString and return *Successful!* if failed return *Failed*;
- isTemplate -- check a whether a VM is template or not and return *True* if it is otherwise return *False*;
- setTemplate -- set a VM to VM template if this operation done as expected return *True* otherwise *False*;

#### Storage Methods
- createPool -- create a storage pool and return *True* if done successfully otherwise return *False*;
- deletePool -- delete a storage pool and return *True* if done successfully otherwise return *False*;
- listPools -- return a string composed by names of pools created and separated by comma but return *False* if something unexpected happened;
- createVolume -- create a volume and return *True* if done successfully otherwise return *False*; 
- deleteVolume -- delete a volume and return *True* if done successfully otherwise return *False*; 
- listVolumes -- list all volumes in a pool specified by a pool name and return a string composed of names of volumes which is separated by comma but return *False* if something unexpected occurred;

#### VIF Methods
- create -- create a VIF according to the provided parameters return uuidString if created successfully, otherwise return *False*;
- delete -- delete a VIF by its uuidString and return *True* if it is done successfully, otherwise return *False*;

#### Contact
*Author: LHearen*

*E-mail: LHearen@126.com*
