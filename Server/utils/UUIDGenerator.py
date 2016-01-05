"""Universal Unique Identifiers (UUIDs).  By default, UUIDs generated here are
purely random, with no internal structure.  However, they are the same size,
and are formatted by the same conventions, as the UUIDs in the Open Software
Foundation's Distributed Computing Environment (OSF DCE).  This allows Xend to
be used with UUIDs generated as per the DCE specification, should that be
required.  These UUIDs are also, by no coincidence, the same size as the
'handle' stored by the Xen hypervisor along with the domain structure."""


import commands
import random


def getUuidUuidgen(randomly = True):
    """Generate a UUID using the command uuidgen.

    If randomly is true (default) generates a random uuid.
    If randomly is false generates a time-based uuid.
    """
    cmd = "uuidgen"
    if randomly:
        cmd += " -r"
    else:
        cmd += " -t"
    return fromString(commands.getoutput(cmd))


def getUuidRandom():
    """Generate a random UUID."""

    return [ random.randint(0, 255) for _ in range(0, 16) ]


#uuidFactory = getUuidUuidgen
uuidFactory = getUuidRandom


def toString(u):
    return "-".join(["%02x" * 4, "%02x" * 2, "%02x" * 2, "%02x" * 2,
                     "%02x" * 6]) % tuple(u)

def fromString(s):
    s = s.replace('-', '')
    return [ int(s[i : i + 2], 16) for i in range(0, 32, 2) ]

def create():
    return uuidFactory()

def createString():
    return toString(create())

def gen_regularUuid():
    return toString(create())
