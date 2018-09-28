const fetchTest = (successCallback, errorCallback) => {
  fetch('api', { method: 'GET' })
    .catch(e => {
      if (errorCallback) errorCallback(e);
    })
    .then(response => response.json())
    .then(jsonData => {
      successCallback(jsonData);
    });
};

export { fetchTest };
