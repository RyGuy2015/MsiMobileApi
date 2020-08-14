package com.dpslink.mobileapi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dpslink.mobileapi.web.rest.TestUtil;

public class DealerStopTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealerStop.class);
        DealerStop dealerStop1 = new DealerStop();
        dealerStop1.setId(1L);
        DealerStop dealerStop2 = new DealerStop();
        dealerStop2.setId(dealerStop1.getId());
        assertThat(dealerStop1).isEqualTo(dealerStop2);
        dealerStop2.setId(2L);
        assertThat(dealerStop1).isNotEqualTo(dealerStop2);
        dealerStop1.setId(null);
        assertThat(dealerStop1).isNotEqualTo(dealerStop2);
    }
}
