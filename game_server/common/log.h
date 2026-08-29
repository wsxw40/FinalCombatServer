#pragma once

#define	LOG_EMERG	0	/* system is unusable */
#define	LOG_ALERT	1	/* action must be taken immediately */
#define	LOG_CRIT	2	/* critical conditions */
#define	LOG_ERROR	3	/* error conditions */
#define	LOG_WARNING	4	/* warning conditions */
#define	LOG_NOTICE	5	/* normal but significant condition */
#define	LOG_INFO	6	/* informational */
#define	LOG_DEBUG1	7	/* debug-level messages */
#define	LOG_DEBUG2	8	/* debug-level messages */
#define	LOG_DEBUG3	9	/* debug-level messages */
#define	LOG_DEBUG4	10	/* debug-level messages */
#define	LOG_DEBUG5	11	/* debug-level messages */

// open log for output, special devices are stdout, stderr, syslog
void log_set_output(const char * device);
const char * log_get_output();

void log_set_ident(const char * format, ...);
const char * log_get_ident();

void log_set_output_level(int level);
int log_get_output_level();

void log_write_sys(int level, const char * format, ...);
void log_write(int level, const char * format, ...);

void DumpBuffer(const char *name, const void *buffer, int size);
