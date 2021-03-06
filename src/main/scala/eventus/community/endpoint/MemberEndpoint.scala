package eventus.community.endpoint

import eventus.common.AppError.handleServerLogicError
import eventus.common.dto.ApiErrorDTO
import eventus.common.types.{CommunityId, MemberId}
import eventus.community.dto.{MemberCreateDTO, MemberIsNotifyDTO}
import eventus.community.endpoint.CommunityEndpoint.communityEndpointRoot
import eventus.community.model.Member
import eventus.community.service.MemberService
import io.circe.generic.auto._
import sttp.capabilities.zio.ZioStreams
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.ztapir.{RichZEndpoint, ZServerEndpoint}
import sttp.tapir.{endpoint, path}

import java.util.UUID

object MemberEndpoint {

  private val memberEndpointRoot = endpoint.in("members").tag("Community")

  val all: List[ZServerEndpoint[MemberService, ZioStreams]] = List(
    communityEndpointRoot.get
      .description("Get list of community's members")
      .in(path[UUID]("communityId"))
      .in("members")
      .out(jsonBody[List[Member]])
      .errorOut(jsonBody[ApiErrorDTO])
      .zServerLogic(uuid =>
        handleServerLogicError(
          MemberService(_.getByCommunityId(CommunityId(uuid)))
        )
      ),
    communityEndpointRoot.post
      .description("Join into community")
      .in(path[UUID]("communityId"))
      .in("join")
      .in(jsonBody[MemberCreateDTO])
      .out(jsonBody[MemberId])
      .errorOut(jsonBody[ApiErrorDTO])
      .zServerLogic(p =>
        handleServerLogicError(
          MemberService(_.create(CommunityId(p._1), p._2))
        )
      ),
    memberEndpointRoot.get
      .description("Get member by its id")
      .in(path[UUID]("memberId"))
      .out(jsonBody[Option[Member]])
      .errorOut(jsonBody[ApiErrorDTO])
      .zServerLogic(id =>
        handleServerLogicError(
          MemberService(_.getById(MemberId(id)))
        )
      ),
    memberEndpointRoot.put
      .description("Update existed member")
      .in("notify")
      .in(jsonBody[MemberIsNotifyDTO])
      .errorOut(jsonBody[ApiErrorDTO])
      .zServerLogic(p =>
        handleServerLogicError(
          MemberService(_.setNotify(p))
        )
      ),
    memberEndpointRoot.delete
      .description("Delete member, also means leave community")
      .in(path[UUID]("memberId"))
      .errorOut(jsonBody[ApiErrorDTO])
      .zServerLogic(id =>
        handleServerLogicError(
          MemberService(_.delete(MemberId(id)))
        )
      )
  )
}
