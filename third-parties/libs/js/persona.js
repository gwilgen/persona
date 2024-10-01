val prevReady = document.ready;
document.ready = () => {
    prevReady();
    console.log('js lib bound')
}