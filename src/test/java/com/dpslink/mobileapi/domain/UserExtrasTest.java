package com.dpslink.mobileapi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dpslink.mobileapi.web.rest.TestUtil;

public class UserExtrasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserExtras.class);
        UserExtras userExtras1 = new UserExtras();
        userExtras1.setId(1L);
        UserExtras userExtras2 = new UserExtras();
        userExtras2.setId(userExtras1.getId());
        assertThat(userExtras1).isEqualTo(userExtras2);
        userExtras2.setId(2L);
        assertThat(userExtras1).isNotEqualTo(userExtras2);
        userExtras1.setId(null);
        assertThat(userExtras1).isNotEqualTo(userExtras2);
    }
}
