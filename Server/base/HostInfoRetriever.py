'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2016-01-12 11:19
Description : Used to retrieve all the basic profile of CPU, Mem, Disk and
            Network in a physical host including their corresponding speed
            and utilization values.
'''
import sys
sys.path.append('/home/lhearen/Server')
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
        ret = executeShellCommand("../shells/cpu_info.sh")
        ret = ret[0]
        # turn string to a dictionary
        ret = json.loads(ret)
        arch = ret["Architecture"].strip()
        byteOrder = ret["Byte Order"].strip()
        numberOfCpus = ret["CPU(s)"].strip()
        name = ret["Model name"].strip()
        ret = executeShellScripts("/proc/version", "cat")
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
        print ret
        del ret[-1]
        print ret
        print ret[0].split(':')[2].split('(')[0]
        print ret[1].split(':')[2].split('(')[0]
        ret = [s.split(':')[2].split('(')[0].strip() for s in ret]
        print ret

    @staticmethod
    def init():
        HostRetriever.__initMem()
        # HostRetriever.__initCpu()
    @staticmethod
    def test():
        HostRetriever.__initNetwork()

# HostRetriever.init()
HostRetriever.test()
