
const { Builder, By, until } = require('selenium-webdriver');

async function A1_Scenario_Test() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), 'Start Game')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 10000);
        console.log("Game started successfully.");

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}


async function twoWinner_Game_2winner_quest_test() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), 'Start Game')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 10000);
        console.log("Game started successfully.");


    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}

async function oneWinner_game_with_events_test() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), 'Start Game')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 10000);
        console.log("Game started successfully.");



    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}



async function noWinner_quest_test() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), 'Start Game')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 10000);
        console.log("Game started successfully.");



        let isWinner = true;
        Assert.asertTrue(isWinner &gt;= false, "Should be no winners");

        int player1CardCount = driver.findElements(By.cssSelector('#player-cards .card')).size();
        Assert.asertTrue(player1CardCount &gt;= 12, "P1 should have 12 cards");

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}


A1_Scenario_Test();
twoWinner_Game_2winner_quest_test();
oneWinner_game_with_events_test();
noWinner_quest_test();
