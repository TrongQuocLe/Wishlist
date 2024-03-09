# Android Project 2 - Wishlist

Submitted by: Trong Le

Wishlist is a wishlist app that helps the user keep track of what they want to buy.

Time spent: 4 hours spent in total

## Required Features

The following **required** functionality is completed:

- [X] **User can add an item to their wishlist**
- [X] **User can see their list of items based on previously inputted items**

The following **optional** features are implemented:

- [X] Wishlist app is 🎨 **customized** 🎨
- [X] User can delete an item by long pressing on the item
- [X] User can open an item's URL by clicking on the item

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src= 'https://github.com/TrongQuocLe/Wishlist/blob/main/wishlist.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<!-- Replace this with whatever GIF tool you used! -->
GIF created with LICEcap
<!-- Recommended tools:
[Kap](https://getkap.co/) for macOS
[ScreenToGif](https://www.screentogif.com/) for Windows
[peek](https://github.com/phw/peek) for Linux. -->

## Notes

ItemAdapter.kt
```
class ItemAdapter(private val items: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTv: TextView = view.findViewById(R.id.itemNameTv)
        val urlTv: TextView = view.findViewById(R.id.urlTv)
        val priceTv: TextView = view.findViewById(R.id.priceTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTv.text = item.name
        holder.priceTv.text = item.price.toString()
        holder.urlTv.text = item.url
        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(holder.itemView.context, browserIntent, null)
            } catch (e: Exception) {
                Toast.makeText(holder.itemView.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }
        holder.itemView.setOnLongClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            true
        }
    }

    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
    ...
}

```
MainActivity.kt
```
    private val items = mutableListOf<Item>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        ...

        val recyclerView = findViewById<RecyclerView>(R.id.recycleview)
        adapter = ItemAdapter(items)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        ...
        submitButton.setOnClickListener {
            ...
            val item = Item(name, price, url)
            adapter.addItem(item)
```

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
