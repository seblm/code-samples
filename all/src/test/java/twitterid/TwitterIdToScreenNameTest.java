package twitterid;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TwitterIdToScreenNameTest {

  @Test
  public void should_convert_id_to_screename() {
    Integer seblmId = 15370457;

    Optional<String> seblmScreenName = TwitterIdToScreenName.getScreenNameFromId(seblmId);

    assertThat(seblmScreenName).isEqualTo(Optional.of("seblm"));
  }

}
