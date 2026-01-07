package attendance.dto;

import attendance.domain.Penalty;

public record UserPenaltyDTO(
        String name,
        int late_count,
        int absence_count,
        Penalty penalty
) {
}
