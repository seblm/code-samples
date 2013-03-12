package twitterid;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class TwitterIdToScreenNameTest {

  @Test
  public void should_convert_id_to_screename() throws Exception {
    Integer seblmId = 15370457;

    String seblmScreenName = TwitterIdToScreenName.getScreenNameFromId(seblmId);

    assertThat(seblmScreenName).isEqualTo("seblm");
  }

}
