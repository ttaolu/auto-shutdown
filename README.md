# auto-shutdown
当下载完成后需要自动关机时 适用此脚本

#### 配置说明
位于脚本中config属性, 可根据需求适当调整
- `lowTimesThreshold: 5`  当下载速率连续低于指定值(lowMBps)5次时 执行关机
- `lowMBps          : 10` 当下载速率低于10MBps时.记录次数加一
- `sleepIntervalSec : 3`  统计下载流量时间间隔为3秒
- `shutdownHolder   : 10` 当执行关机时, 推迟10秒后关机

> 注意: 请确保启动该脚本前 所有需要保存的工作文件等都已保存

