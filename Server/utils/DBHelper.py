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
        return db.VMs

    @staticmethod
    def insert(dataDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-04 14:02
        Description : Used to insert the whole VM parameters to VM collection;
        '''
        col = VMHelper.__getVMCollection()
        res = col.insert(dataDict)
        return res

    @staticmethod
    def update(filterDict, dataDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-04 14:32
        Description : Update certain fields in specified documents;
        '''
        col = VMHelper.__getVMCollection()
        params = {"$set": dataDict}
        res = col.update(filterDict, params)
        return res



    @staticmethod
    def retrieve(filterDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 24
        Description : Using filterJson to filter the result from collection vm;
        Parameter   : filterDict can be dictionary of list of dictionary;
        Return      : return the documents fitting the condition;
        '''
        col = VMHelper.__getVMCollection()
        print col.find_one()
        return col.find_one(filterDict)

    @staticmethod
    def remove(filterDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 42
        Description : Used to remove certain documents in VM collection;
        Parameter   : filterDict can be dictionary of list of dictionary;
        Return      : the amount of documents removed;
        '''
        return VMHelper.__getVMCollection().remove(filterDict)

class VBDHelper():
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-31 11:00
    Description : Used to access and remove documents in StoragePools and Volumes;
    '''

    @staticmethod
    def __getPoolCollection():
        client = MongoClient('localhost', 27017)
        db = client['server']
        return db.StoragePools

    @staticmethod
    def __getVolumeCollection():
        client = MongoClient('localhost', 27017)
        db = client['server']
        return db.Volumes

    @staticmethod
    def removePool(filterDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-31 11:04
        Description : Using a dictionary filterDict to remove some documents;
        '''
        return VBDHelper.__getPoolCollection().remove(filterDict)

    @staticmethod
    def removeVolume(filterDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-31 11:04
        Description : Using a dictionary filterDict to remove some volumes;
        '''
        return VBDHelper.__getVolumeCollection().remove(filterDict)

class VIFHelper:
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-05 10:36
    Description : Used to carry out some mongodb operations;
    '''
    @staticmethod
    def __getCollection():
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 45
        Description : Used to provide vm collection object for further use;
        '''
        client = MongoClient('localhost', 27017)
        db = client['server']
        return db.VIFs

    @staticmethod
    def update(filterDict, dataDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-04 14:32
        Description : Update certain fields in VIFs filtered documents;
        '''
        col = VIFHelper.__getCollection()
        params = {"$set": dataDict}
        res = col.update(filterDict, params)
        return res

    @staticmethod
    def retrieve(filterDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 24
        Description : Using filterJson to filter the result from collection vm;
        Parameter   : filterDict can be dictionary of list of dictionary;
        Return      : return the documents fitting the condition;
        '''
        col = VIFHelper.__getCollection()
        return col.find_one(filterDict)

    @staticmethod
    def remove(filterDict):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-17 14 : 42
        Description : Used to remove certain documents in VIFs collection;
        Parameter   : filterDict can be dictionary of list of dictionary;
        Return      : the amount of documents removed;
        '''
        print "inside VIFHelper remove"
        return VIFHelper.__getCollection().remove(filterDict)
