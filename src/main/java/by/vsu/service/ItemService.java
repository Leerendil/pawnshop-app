package by.vsu.service;

import by.vsu.repository.ItemsRepository;
import by.vsu.tableClasses.Items;

public class ItemService extends Service<Items> {

    public ItemService(ItemsRepository itemsRepository) {
        super(itemsRepository);
    }

}