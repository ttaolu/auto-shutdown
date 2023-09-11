import java.util.concurrent.TimeUnit

/*
 * Auth: taolu
 * Date: 2023/09/11 10:17
 */

def config = [
        lowTimesThreshold: 5,
        lowMBps          : 10,
        sleepIntervalSec : 3,
	shutdownHolder   : 10
]

def receivedBytes = {
    def res = 'cmd /c netstat -e'.execute().text.lines().find { it ==~ /\w+(\s+\d+){2}/ }
    def m = res =~ /\d+/
    m.find()
    def grep = m.group() as Long
    println "line: $res => $grep"
    grep
}

def toMBps = { nowBytes, nowNano, preBytes, preNano ->
    def durationSec = (nowNano - preNano) / 1000_000_000
    def bytes = nowBytes - preBytes
    def resMBps = bytes > 0 ? bytes / (1 << 20) / durationSec : 0
    println "preBytes: $preBytes, nowBytes: $nowBytes, bytes: $bytes, preNano: $preNano, nowNano: $nowNano, durationSec: $durationSec, MBps: $resMBps"
    resMBps
}

def shutDown = { t ->
    println "will shutdown in second $t"
    println "cmd /c shutdown -s -t $t".execute().text
}

def pre = receivedBytes()
def preTime = System.nanoTime()
def lowTimes = 0

while (lowTimes < config.lowTimesThreshold) {
    sleep TimeUnit.SECONDS.toMillis(config.sleepIntervalSec)
    def recv = receivedBytes()
    def time = System.nanoTime()
    lowTimes = toMBps(recv, time, pre, preTime) < config.lowMBps ? lowTimes + 1 : 0
    println "lowTimes: $lowTimes"
    pre = recv
    preTime = time
}
shutDown config.shutdownHolder

sleep TimeUnit.SECONDS.toMillis(config.shutdownHolder) 





