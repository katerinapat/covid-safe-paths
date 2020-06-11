/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package covidsafepaths.bte.exposurenotifications.storage;

import androidx.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * A token used when calling provideDiagnosisKeys to identify a given call to the API.
 *
 * <p>Partners should implement a daily TTL/expiry, for on-device storage of this data, and must
 * ensure compliance with all applicable laws and requirements with respect to encryption, storage,
 * and retention polices for end user data.
 */
public class TokenEntity {

  @NonNull
  private String token;

  private long lastUpdatedTimestampMs;

  private boolean responded;

  TokenEntity(@NonNull String token, boolean responded) {
    this.lastUpdatedTimestampMs = System.currentTimeMillis();
    this.token = token;
    this.responded = responded;
  }

  /**
   * Creates a TokenEntity.
   *
   * @param token the token identifier
   * @param responded whether the exposure notification API has responded for the given token
   */
  public static TokenEntity create(@NonNull String token, boolean responded) {
    return new TokenEntity(Preconditions.checkNotNull(token), responded);
  }

  public long getLastUpdatedTimestampMs() {
    return lastUpdatedTimestampMs;
  }

  void setLastUpdatedTimestampMs(long ms) {
    this.lastUpdatedTimestampMs = ms;
  }

  @NonNull
  public String getToken() {
    return token;
  }

  public void setToken(@NonNull String token) {
    this.token = token;
  }

  public boolean isResponded() {
    return responded;
  }

  public void setResponded(boolean responded) {
    this.responded = responded;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenEntity that = (TokenEntity) o;
    return lastUpdatedTimestampMs == that.lastUpdatedTimestampMs &&
        responded == that.responded &&
        token.equals(that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, lastUpdatedTimestampMs, responded);
  }

}
