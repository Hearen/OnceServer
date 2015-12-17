'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-16 10 : 28
Description : Used to assist other modules;
'''

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

