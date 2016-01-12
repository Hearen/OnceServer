from utils.Connection import Connection
conn = Connection.get_libvirt_connection()

print "capabilities"
print conn.getCapabilities()
print "getcpumap"
print conn.getCPUMap()
print "getFreeMemory"
print conn.getFreeMemory()
