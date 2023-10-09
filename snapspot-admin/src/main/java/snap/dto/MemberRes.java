package snap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;

@Getter
@NoArgsConstructor
public class MemberRes {

	private Member member;

	public MemberRes(Member member){
		this.member = member;
	}
}
