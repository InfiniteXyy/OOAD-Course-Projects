const fetchTest = obj => {
  // length velocity positions
  let str = Object.entries(obj)
    .map(([key, val]) => `${key}=${val}`)
    .join('&');
  return fetch('api?' + str, { method: 'GET' }).then(response => response.json());
};

export { fetchTest };
