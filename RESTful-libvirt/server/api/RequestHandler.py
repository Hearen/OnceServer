import traceback
import json
import ast
from server import app
from flask import request, make_response
from util.OnceLogging import log, init

init("/var/log/xen/libvirt.log", "DEBUG", log)

@app.before_request
def before():
    print '\n\n\n\n\n\n\n\n'
    print'the request object ready to be processed:', request
    # print("*************************************")
    moduleName = str(request.headers.get('Module'))
    methodName = str(request.headers.get('Method'))
    # params = request.headers.get('Params')
    # params = ast.literal_eval(str(params))
    # print("*********************************")
    print dump_request_detail(request)
    # print("************request.data*********************")
    # print request.data
    # # print("************request.data dic*********************")
    # # dataDic = json.loads(request.data)
    # # print dataDic
    # print("************request.form*********************")
    # print request.form
    params = request.form.to_dict()
    # print params
    # for key in params.keys():
        # print(key + ":" + str(params.get(key)))
    # print("************request.values*********************")
    # print(request.values)
    # module = __import__(moduleName, globals={"__name__": __name__})
    # method = getattr(module, methodName)
    # retv = method(**params)
    # print retv
    try:
        print "inside try block"
        module = __import__(moduleName, globals={"__name__": __name__})
        print module
        if module is not None:
            method = getattr(module, methodName)

            retv = method(**params)
            print("after calling " + methodName)
            print retv
            if not retv:
                print "Failed in personal functions"
                headers = {'Content-Type':'text/plain'}
                return make_response("User function failed", 403, headers)
        print "after trying to call"
    except Exception:
        log.exception(traceback.format_exc())
        headers = {'Content-Type':'text/plain'}
        return make_response("User function failed", 403, headers)
# #         request.data = str(retv[1])

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

