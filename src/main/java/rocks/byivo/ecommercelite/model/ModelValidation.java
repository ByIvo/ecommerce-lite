/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.model;

/**
 *
 * @author byivo
 */
public class ModelValidation {
    
    public static class Item {
        public static final String FIELD_NAME = "name";
        public static final String FIELD_BOUGHT_PRICE = "boughtPrice";
        public static final String FIELD_DESCRIPTION = "description";
        
        public static final String INVALID_NAME = "Você precisa informar um nome!";
        public static final String NAME_TOO_LONG = "O nome do item não pode passar de 50 caracteres!";
        public static final String INVALID_DESCRIPTION = "Informe uma descrição válida!";
        public static final String DESCRIPTION_TOO_LONG = "A descrição do produto não pode ser maior que 200 caracteres!";
        
        public static final String INVALID_BOUGHT_PRICE = "Você precisa informar um valor de compra válido!";
    }
    
    public static class ItemBuy {
        public static final String FIELD_ITEM = "item";
        public static final String FIELD_BUY = "buy";
        public static final String FIELD_ITEM_QNT = "itemQnt";
        
        public static final String INVALID_ITEM = "Você precisa informar um produto válido!";
        public static final String INVALID_ITEM_BOUGHT_PRICE = "O produto precisa ter um preço!";
        public static final String INVALID_BUY = "O nome do item não pode passar de 50 caracteres!";
        public static final String INVALID_ITEM_QNT = "Você precisa adicionar pelo menos um item";
    }
    
    public static class Buy {
        public static final String FIELD_BOUGHT_ITEM = "boughtItems";
        public static final String FIELD_TOTAL_EXPENSES = "totalExpenses";
        public static final String FIELD_PROFIT_RATE = "profitRate";
        public static final String FIELD_BUY_DATE = "buyDate";
        
        public static final String INVALID_BOUGHT_ITEM = "Você precisa comprar ao menos um produto";
        public static final String INVALID_PROFIT_RATE = "Você deve fornecer apenas uma porcentagem (0% à 100%) de lucro!";
        public static final String INVALID_TOTAL_EXPENSES = "Você deve fornecer o valor total das despesas que quer diluir na venda";
        public static final String INVALID_BUY_DATE = "Ocorreu um problema com a data de venda";
    }
}
