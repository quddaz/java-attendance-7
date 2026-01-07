package attendance.dto;

import attendance.domain.AttendanceStatus;

public class AttendanceCountDTO{
    private int attendance_count;
    private int late_count;
    private int absence_count;

    public AttendanceCountDTO() {
        attendance_count = 0;
        late_count = 0;
        absence_count = 0;
    }

    public void plus(AttendanceStatus attendanceStatus){
        if(attendanceStatus == AttendanceStatus.ATTENDANCE)
            attendance_count++;
        if(attendanceStatus == AttendanceStatus.ABSENCE)
            absence_count++;
        if(attendanceStatus == AttendanceStatus.LATE)
            late_count++;
    }

}
