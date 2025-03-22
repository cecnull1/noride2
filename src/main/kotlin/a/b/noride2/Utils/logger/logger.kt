package a.b.noride2.Utils.logger

import org.apache.logging.log4j.Logger

// 为每个日志级别提供无参数版本
fun Logger.debug() = this.debug("Default debug message")
fun Logger.info() = this.info("Default info message")
fun Logger.warn() = this.warn("Default warn message")
fun Logger.error() = this.error("Default error message")
fun Logger.fatal() = this.fatal("Default fatal message")