package eventus.repository

import eventus.error.RepositoryError
import eventus.model.Participant
import zio.IO

import java.util.UUID

trait ParticipantRepository {
  def filterById(id: UUID): IO[RepositoryError, Option[Participant]]
  def filter(
      eventId: UUID,
      memberId: Option[UUID]
  ): IO[RepositoryError, List[Participant]]
  def insert(participant: Participant): IO[RepositoryError, Unit]
  def delete(id: UUID): IO[RepositoryError, Unit]
}