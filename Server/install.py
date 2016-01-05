import traceback
from eve import Eve
from utils.MyUUID import UUIDEncoder
from utils.MyUUID import UUIDValidator
from flask import request
from utils.OnceLogging import log, init
from utils.Tools import dumpRequest
from utils.DBEventHandler import inserting
from utils.DBEventHandler import VMInserting
from utils.DBEventHandler import VolumeInserting
from utils.DBEventHandler import PoolInserting
from utils.DBEventHandler import VIFInserting
from utils.Tools import moduleLoader
from utils.Tools import errorResponseMaker
from utils.Tools import responseMaker



def hello(resource, item):
    print "\n\non_pre_POST is here!\n\n"

def test(items):
    print "testing volumes"
app = Eve(__name__, json_encoder=UUIDEncoder, validator=UUIDValidator)
app.on_insert += inserting
app.on_insert_VMs += VMInserting
app.on_insert_Volumes += VolumeInserting
app.on_insert_StoragePools += PoolInserting
app.on_insert_VIFs += VIFInserting
# app.on_insert_Volumes += test
app.on_pre_POST += hello

init("/var/log/xen/libvirt.log", "DEBUG", log)


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
        ret = method(**params)
        print ret
        print methodName
        print('create' not in methodName)
        if 'create' not in methodName:
            print "it's not create*"
            if ret:
                print "executed successfully!"
                return responseMaker(ret)
            else:
                print "Failed!"
                return errorResponseMaker()
        try:
            print "inside try block"
#             module = moduleLoader('base', moduleName)
#             print module
#             method = getattr(module, methodName)
#             retv = method(**params)
            if not ret:
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
    return response



if __name__ == '__main__':
    app.run(host='133.133.135.13', port=5100, debug=True, threaded=True)
