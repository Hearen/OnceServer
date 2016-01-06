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
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-06 10:52
    Description: Used to execute commands from clients;
    '''
    print '\n\n\nStart to handle request...'
    print dumpRequest(request)
    data = request.form.to_dict()
    print data
    if 'Module' in data:
        moduleName = data['Module']
        methodName = data['Method']
        del data['Module']
        del data['Method']
        params = data
        print params
        module = moduleLoader('base', str(moduleName))
        method = getattr(module, methodName)
        ret = method(**params)
        print "return value"
        print ret
        if 'create' not in methodName:
            print "it's not create*"
            print(ret == None)
            if ret != None:
                print "executed successfully!"
                print str(ret)
                return responseMaker(ret)
            else:
                print "Failed!"
                return errorResponseMaker()

@app.after_request
def after(response):
    return response



if __name__ == '__main__':
    app.run(host='133.133.135.13', port=5100, debug=True, threaded=True)
