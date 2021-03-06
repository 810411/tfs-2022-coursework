package eventus.event.dto

import eventus.common.types.MemberId

case class ReviewCreateDTO(
    memberId: MemberId,
    rating: Int,
    feedback: String
)
