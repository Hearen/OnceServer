'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-16 10 : 28
Description : Used to assist other modules;
'''
from flask import make_response
from utils.OnceLogging import log, init
import subprocess
init("/var/log/xen/libvirt.log", "DEBUG", log)

def moduleLoader(packageName, moduleName):
    '''
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-15 15:19
    Description: Used to load a module from the package
                will only return the module but not introduce
                the module to the current context unlike importlib;
    '''
    package = __import__(packageName, globals(), locals(), moduleName.split(), -1)
    return getattr(package, moduleName)

def dumpRequest(request):
    '''
    Author      : DBear
    Time        : 2015-12-15 15 : 33
    Description : Present the details of the coming request;
    '''
    request_detail = """
        request.endpoint:{request.endpoint}
        request.method:{request.method}
        request.view_args:{request.view_args}
        request.args:{request.args}
        request.form:{request.form}
        request.user_agent:{request.user_agent}
        request.files:{request.files}
        request.is_xhr:{request.is_xhr}
        {request.headers}""".format(request=request).strip()
    return request_detail

def errorResponseMaker():
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-15 15 : 30
    Description : Used to handle exception response;
    '''
    headers = {'Content-Type':'text/plain'}
    return make_response("User function failed", 403, headers)

def responseMaker(content):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-05 11:18
    Description : Used to handle methods' reponse except create* ones;
    '''
    headers = {'Content-Type':'text/plain'}
    return make_response(str(content), 200, headers)

def logNotFound(objectName, NameOrId, message):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-07 11:09
    Description : Used to log not found error in libvirt.log;
    '''
    log.debug("%s %s Not Found! Message: %s" % (objectName, NameOrId, message))

def executeShellCommand(shellDir, executor='/bin/bash'):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-11 14:37
    Description : Used to execute a shell script and return output from stdout;
    '''
    process = subprocess.Popen([executor, shellDir], shell=False, \
                               stdout=subprocess.PIPE)
    ret = process.communicate()
    return ret
