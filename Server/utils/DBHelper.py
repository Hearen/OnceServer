'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-17 14 : 24
Description : encapsulate all the mongodb operations to assist other modules;
'''
from pymongo import MongoClient

class VMHelper():
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-17 14 : 43
    Description : All MongoDB operations will be covered here;
    '''

    @staticmethod
    def __getVMCollection():
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 45
        Description : Used to provide vm collection object for further use;
        '''
        client = MongoClient('localhost', 27017)
        db = client['server']
        return db.VM


    @staticmethod
    def retrieve(filter):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 24
        Description : Using filterJson to filter the result from collection vm;
        Parameter   : filter can be dictionary of list of dictionary;
        Return      : return the documents fitting the condition;
        '''
        return VMHelper.__getVMCollection().find_one(filter)

    @staticmethod
    def remove(filter):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 42
        Description : Used to remove certain documents in VM collection;
        Parameter   : filter can be dictionary of list of dictionary;
        Return      : the amount of documents removed;
        '''
        return VMHelper.__getVMCollection().delete_many(filter)

