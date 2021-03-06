package eventus.community.repository

import eventus.common.RepositoryError
import eventus.common.types.{CommunityId, MemberId}
import eventus.community.model.Member
import zio.IO

trait MemberRepository {
  def filterByCommunityId(
      communityId: CommunityId
  ): IO[RepositoryError, List[Member]]
  def filterById(id: MemberId): IO[RepositoryError, Option[Member]]
  def insert(member: Member): IO[RepositoryError, Unit]
  def delete(id: MemberId): IO[RepositoryError, Unit]
  def updateIsNotify(id: MemberId, isNotify: Boolean): IO[RepositoryError, Unit]
}
