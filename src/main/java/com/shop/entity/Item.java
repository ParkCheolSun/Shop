package com.shop.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shop.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 상품 코드

	@Column(nullable = false, length = 50)
	private String itemNm; // 상품명

	@Column(name = "price", nullable = false)
	private int price; // 가격

	@Column(nullable = false)
	private int stockNumber; // 재고수량

	@Lob
	@Column(nullable = false)
	private String itemDetail; // 상품 상세 설명

	@ManyToOne(targetEntity = com.shop.entity.Member.class)
	@JoinTable(name = "member_item", 
	joinColumns = @JoinColumn(name = "member_id"), 
	inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Member> member;

	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus; // 상품 판매 상태
}
