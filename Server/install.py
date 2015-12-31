import traceback
from eve import Eve
from utils.MyUUID import UUIDEncoder
from utils.MyUUID import UUIDValidator
from flask import request, make_response
from utils.OnceLogging import log, init
from utils.Tools import dumpRequest
from utils.DBEventHandler import inserting
from utils.DBEventHandler import VMInserting
from utils.Tools import moduleLoader

def hello(resource, item):
    print "\n\non_pre_POST is here!\n\n"
app = Eve(__name__, json_encoder=UUIDEncoder, validator=UUIDValidator)
app.on_insert += inserting
app.on_insert_VM += VMInserting
app.on_pre_POST += hello

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
    print dumpRequest(request)
    data = request.form.to_dict()
    print data
    moduleName = data['Module']
    del data['Module']
    print data
    methodName = data['Method']
    del data['Method']
    print data
    print moduleName
    if moduleName != None:
        params = data
        print params
        module = moduleLoader('base', str(moduleName))
        print module
        method = getattr(module, methodName)
        retv = method(**params)
        print retv
        try:
            print "inside try block"
#             module = moduleLoader('base', moduleName)
#             print module
#             method = getattr(module, methodName)
#             retv = method(**params)
            if not retv:
                print "Wrong result from customized function!"
                errorResponseMaker()
            print "leaving try block"
            # make_response will avoid Eve data validation and DB insertion
            # headers = {'Content-Type':'text/plain'}
            # return make_response("Successful!", 201, headers)
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
    app.run(host='133.133.135.13', port=5100, debug=True, threaded=True)
