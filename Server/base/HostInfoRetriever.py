'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2016-01-12 11:19
Description : Used to retrieve all the basic profile of CPU, Mem, Disk and
            Network in a physical host including their corresponding speed
            and utilization values.
'''
import os
rootDir = os.getcwd()[0:-5]
import sys
sys.path.append(rootDir)
import json
from utils.Tools import executeShellScripts
from utils.Tools import executeShellCommand
from utils.DBHelper import HostHelper
class HostRetriever():
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-12 13:50
    Description : Used to retrieve kernel version, cpus, frequency and Arch;
    '''

    @staticmethod
    def __initCpu():
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-12 15:36
        Description : get all the static cpu profile and store it in db;
        '''
        ret = executeShellScripts("../shells/cpu_info.sh")
        ret = ret[0]
        # turn string to a dictionary
        ret = json.loads(ret)
        arch = ret["Architecture"].strip()
        byteOrder = ret["Byte Order"].strip()
        numberOfCpus = ret["CPU(s)"].strip()
        name = ret["Model name"].strip()
        ret = executeShellCommand("cat /proc/version")
        version = ret[0].strip()
        dataDict = {"cpu.Architecture": arch, "cpu.ByteOrder": byteOrder,
                    "cpu.cpus": numberOfCpus, "cpu.Model": name,
                    "cpu.version": version}
        ret = HostHelper.update({"_id": 1}, dataDict)
        print ret

    @staticmethod
    def __initMem():
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-12 15:37
        Description : get the static total, used and free amount of memory and
                    it utilization and store them in db;
        '''
        ret = executeShellScripts("../shells/current_ram.sh")[0]
        print ret
        ret = json.loads(ret)
        total = int(ret["total"])
        used = int(ret["used"])
        free = int(ret["free"])
        dataDict = {"mem.total": str(total)+"M", "mem.used": str(used)+"M",
                    "mem.free": str(free)+"M"}
        ret = HostHelper.update({"_id":1}, dataDict)
        print ret

    @staticmethod
    def __initNetwork():
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-12 16:21
        Description : Used to retrieve the physical network cards profile;
        '''
        ret = executeShellCommand("lspci | grep Net")[0]
        ret = ret.split('\n')
        del ret[-1]
        ret = [s.split(':')[2].split('(')[0].strip() for s in ret]
        ret = HostHelper.update({"_id": 1}, {"network": ret})
        print ret

    @staticmethod
    def __initDisk():
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-13 09:41
        Description : Used to get total, used and free info of partitions and
                    store them in db;
        '''
        ret = executeShellCommand('df -h')[0].split('\n')
        # print ret
        del ret[0]
        del ret[-1]
        dataList = []
        for item in ret:
            itemDict = {}
            itemList = item.split()
            itemDict["fileSystem"] = itemList[0]
            itemDict["total"] = itemList[1]
            itemDict["used"] = itemList[2]
            itemDict["free"] = itemList[3]
            itemDict["mountedOn"] = itemList[5]
            dataList.append(itemDict)
            print itemList
        print dataList
        ret = HostHelper.update({"_id": 1}, {"disk": dataList})
        print ret

    @staticmethod
    def init():
        HostRetriever.__initMem()
        HostRetriever.__initDisk()
        HostRetriever.__initNetwork()
        HostRetriever.__initCpu()

    @staticmethod
    def test():
        # HostRetriever.__initNetwork()
        HostRetriever.__initDisk()

HostRetriever.init()
# HostRetriever.test()
