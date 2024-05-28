# auto-shutdown

## What Is It?

此脚本适用于 Windows 操作系统在下载任务完成后自动关机

原理为通过 window 系统的命令 `netstat` 获取下载流量,  
通过计算得出下载速度, 并判断速度连续小于设定的值一定次数后  
以此推断为下载任务已经完成, 然后执行关机命令 `shutdown` 关机  

获取流量来源于命令输出的第一个数字, 如下图  
![netstat](windows.netstat.png)

## 依赖

运行脚本需要依赖如下
- [jre 8+](https://www.oracle.com/java/technologies/downloads/)
- [groovy 3.0+](https://groovy.apache.org/download.html) 安装引导 [groovy-install](https://groovy-lang.org/install.html)


## 操作步骤

1. 执行下载任务并确保下载速度大于阈值
2. 保存所有工作和文件, 为关机做好准备
3. 双击执行脚本


> 注意: 请确保启动该脚本前 所有需要保存的工作文件等都已保存关闭, 否则关机后可能造成损失！

## 配置说明
位于脚本中config属性, 可根据需求适当调整
- `lowTimesThreshold: 5`  判断次数: 5  = 当下载速率连续低于指定的速度阈值(lowMBps)5次时 执行关机
- `lowMBps          : 10` 速度阈值: 10 = 当下载速率低于10MBps时.记录次数加一
- `sleepIntervalSec : 3`  采样间隔: 3  = 统计下载流量时间间隔为，默认3秒进行一次流量采样，并计算下载速度
- `shutdownHolder   : 10` 关机延迟: 10 = 当执行关机时, 延迟多少秒后关机

## Thanks

**Thank you !**
