num = [1, 2, 3, 4, 5]
chunkSize = 3
const res = [];

for (let i = 0; i < num.length; i += chunkSize) {
  res.push(num.slice(i, i + chunkSize))
}

console.log(res)
