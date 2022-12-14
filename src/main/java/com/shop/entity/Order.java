package com.shop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shop.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders") // 정렬할 때 사용하는 키워드 ‘order’가 있기 때문에 엔티티에 매핑되는 테이블로 ‘orders’로 지정
@Getter
@Setter
public class Order extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne // 한 명의 회원은 여러 번 주문을 할 수 있으므로 주문 엔티티 기준에서 다대일 단방향 매핑을 한다.
	@JoinColumn(name = "member_id")
	private Member member;
	private LocalDateTime orderDate; // 주문일
	
	@Enumerated(EnumType.STRING)	
	private OrderStatus orderStatus; // 주문상태
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) // 주문 상품 엔티티와 일대다 매핑한다. 외래키가 있는 주문 상품이 연관관계의 주인이다.
	private List<OrderItem> orderItems = new ArrayList<>(); // 하나의 주문에 여러 개 주문 상품을 갖기에 List 자료형 사용

}
