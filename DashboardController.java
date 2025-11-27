public class DashboardController {
    private DashboardView view;
    private BankingApp bankingApp;
    
    public DashboardController(DashboardView view, BankingApp bankingApp) {
        this.view = view;
        this.bankingApp = bankingApp;
        setupEventHandlers();
    }
    
    private void setupEventHandlers() {
        view.viewAccountsButton.setOnAction(e -> bankingApp.showAccountsView());
        view.depositButton.setOnAction(e -> bankingApp.showDepositView());
        view.withdrawButton.setOnAction(e -> bankingApp.showWithdrawView());
        view.transactionHistoryButton.setOnAction(e -> bankingApp.showTransactionHistoryView());
        view.logoutButton.setOnAction(e -> bankingApp.logout());
        view.openAccountButton.setOnAction(e -> bankingApp.showOpenAccountView());
    }
}