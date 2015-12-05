import traceback
import ast
from server import app
from flask import request, make_response
from util.OnceLogging import log, init

init("/var/log/xen/libvirt.log", "DEBUG", log)

@app.before_request
def before():
    print'the request object ready to be processed:', request
    print("*************************************")
    # print "request.headers", request.headers
    moduleName = str(request.headers.get('Module'))
    print moduleName
    methodName = str(request.headers.get('Method'))
    print methodName
    params = request.headers.get('Params')
    print params
    params = ast.literal_eval(str(params))
    for key in params.keys():
        print(key + ":" + params.get(key))
    print("*********************************")
    # print dump_request_detail(request)
    # print("*********************************")
    try:
        print "inside try block"
        module = __import__(moduleName, globals={"__name__": __name__})
        method = getattr(module, methodName)

        retv = method(**params)
        print retv
        if not retv:
            headers = {'Content-Type':'text/plain'}
            return make_response("User function failed", 403, headers)
    except Exception:
        log.exception(traceback.format_exc())
        headers = {'Content-Type':'text/plain'}
        return make_response("User function failed", 403, headers)
#         request.data = str(retv[1])

@app.after_request
def after(response):
    """
    Your function must take one parameter, a `response_class` object and return
    a new response object or the same (see Flask documentation).
    """
    # print('and here we have the response object instead:', response)
    print("************************")
    # print("response details:")
    return response

