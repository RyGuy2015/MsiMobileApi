package com.dpslink.mobileapi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dpslink.mobileapi.web.rest.TestUtil;

public class StopTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stop.class);
        Stop stop1 = new Stop();
        stop1.setId(1L);
        Stop stop2 = new Stop();
        stop2.setId(stop1.getId());
        assertThat(stop1).isEqualTo(stop2);
        stop2.setId(2L);
        assertThat(stop1).isNotEqualTo(stop2);
        stop1.setId(null);
        assertThat(stop1).isNotEqualTo(stop2);
    }
}
