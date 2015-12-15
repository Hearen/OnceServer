import traceback
from server import app
from flask import request, make_response
from OnceLogging import log, init
import base.VM

init("/var/log/xen/libvirt.log", "DEBUG", log)

def errorResponseMaker():
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
    moduleName = str(request.headers.get('Module'))
    methodName = str(request.headers.get('Method'))
    print dump_request_detail(request)
    params = request.form.to_dict()
    module = __import__(moduleName, globals={"__name__": __name__})
    method = getattr(module, methodName)
    retv = method(**params)
    try:
        print "inside try block"
        print moduleName
        if moduleName != "None":
            print moduleName
            module = __import__(moduleName, globals={"__name__": __name__})
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
    return response

def dump_request_detail(request):
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

