'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-16 10 : 28
Description : Used to assist other modules;
'''
from flask import make_response

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
    return make_response(str(content), 403, headers)

