package am.davsoft.sfl_assessment.service.impl;

import am.davsoft.sfl_assessment.model.CafeOrder;
import am.davsoft.sfl_assessment.model.OrderState;
import am.davsoft.sfl_assessment.model.Status;
import am.davsoft.sfl_assessment.repository.OrderRepository;
import am.davsoft.sfl_assessment.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CafeOrder create(CafeOrder order) {
        order.setOrderState(OrderState.OPEN);
        order.setStatus(Status.ACTIVE);
        CafeOrder newOrder = orderRepository.save(order);
        log.info("IN create - order successfully created with id: {}", newOrder.getId());
        return newOrder;
    }

    @Override
    public CafeOrder update(CafeOrder order) {
        CafeOrder updatedOrder = orderRepository.save(order);
        log.info("IN update - order successfully updated with id: {}", updatedOrder.getId());
        return updatedOrder;
    }

    @Override
    public List<CafeOrder> getAll() {
        List<CafeOrder> orderList = orderRepository.findAll();
        log.info("IN getAll - {} orders found", orderList.size());
        return orderList;
    }

    @Override
    public List<CafeOrder> findAllByWaiterId(Long id) {
        List<CafeOrder> orderList = orderRepository.findAllByWaiter_Id(id);
        log.info("IN findByWaiter - {} orders found", orderList.size());
        return orderList;
    }

    @Override
    public CafeOrder findById(Long id) {
        CafeOrder order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            log.warn("IN findById - no order found by id: {}", id);
            return null;
        }
        log.info("IN findById - order found by id: {}", id);
        return order;
    }

    @Override
    public CafeOrder findByIdAndWaiterId(Long id, Long waiterId) {
        CafeOrder order = orderRepository.findByIdAndWaiter_Id(id, waiterId);
        log.info("IN findByIdAndWaiterId - order found by id: {} and waiterId: {}", id, waiterId);
        return order;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
        log.info("IN delete - order with id: {} successfully deleted", id);
    }
}
