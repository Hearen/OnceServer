from utils.Connection import Connection
from eve import Eve
from utils.MyUUID import UUIDEncoder
from utils.MyUUID import UUIDValidator
import traceback
from flask import request, make_response
from utils.OnceLogging import log, init
from utils.Tools import dumpRequest

def replace_uuid(items):
    print "I am inserting VM now."


def hello(resourceName, items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-16 09 : 54
    Description : Used to test mongoDB insertion event;
    '''
    print "####################################"
    print "hello world"
    print resourceName
    print items
    print "uuid transferred from client"
    print items[0]["_id"]
    name = items[0]["name"]
    print name
    conn = Connection.get_libvirt_connection()
    dom = conn.lookupByName(name)
    uuidString = dom.UUIDString()
    print uuidString
    print 'trying to replace uuit'
    items[0]["_id"] = uuidString
    print "####################################"

app = Eve(__name__, json_encoder=UUIDEncoder, validator=UUIDValidator)
app.on_insert += hello
app.on_insert_VM += replace_uuid

init("/var/log/xen/libvirt.log", "DEBUG", log)


def errorResponseMaker():
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-15 15 : 30
    Description : Used to handle exception response;
    '''
    headers = {'Content-Type':'text/plain'}
    return make_response("User function failed", 403, headers)


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


@app.before_request
def before():
    '''
    Author: DBear
    Re-coded by LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-14 15:46
    Description: Used to execute commands from clients;
    '''
    print '\n\n\nStart to handle request...'
    moduleName = str(request.headers.get('Module'))
    methodName = str(request.headers.get('Method'))
    print dumpRequest(request)
    print moduleName
    params = request.form.to_dict()
    module = moduleLoader('base', moduleName)
    print module
    method = getattr(module, methodName)
    retv = method(**params)
    if moduleName != "None":
        try:
            print "inside try block"
            module = moduleLoader('base', moduleName)
            print module
            method = getattr(module, methodName)
            retv = method(**params)
            if not retv:
                print "Wrong result from customized function!"
                errorResponseMaker()
            print "leaving try block"
        except Exception:
            log.exception(traceback.format_exc())
            errorResponseMaker()


@app.after_request
def after(response):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-15 15 : 32
    Description : Used to further post-process the response
                returned to the clients;
    '''
    return response

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5100, debug=True, threaded=True)
